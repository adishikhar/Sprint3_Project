import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { PromoRequest } from '../types';
import './PromoModal.css';

interface Props {
  visible: boolean;
  onClose: () => void;
  onSuccess: () => void;
}

const PromoModal: React.FC<Props> = ({ visible, onClose, onSuccess }) => {
  const [formData, setFormData] = useState<PromoRequest>({
    promoType: '',
    description: '',
    promoAmount: 0,
  });

  const modalRef = useRef<HTMLFormElement>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'promoAmount' ? +value : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    await axios.post('http://localhost:8080/api/promotions', formData);
    onSuccess();
    onClose();
    setFormData({ promoType: '', description: '', promoAmount: 0 });
  };

  // ✅ Close on outside click
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
        onClose();
      }
    };

    if (visible) {
      document.addEventListener('mousedown', handleClickOutside);
    }

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [visible, onClose]);

  if (!visible) return null;

  return (
    <div className="modal-overlay">
      <form className="modal" onSubmit={handleSubmit} ref={modalRef}>
        <h2>Create Promo Code</h2>

        <select name="promoType" value={formData.promoType} onChange={handleChange} required>
          <option value="">Select Promo Type</option>
          <option value="By Product">By Product</option>
          <option value="By Category">By Category</option>
          <option value="By Order Total">By Order Total</option>
        </select>

        <input
          name="description"
          placeholder="Enter description"
          value={formData.description}
          onChange={handleChange}
          required
        />

        <input
  name="promoAmount"
  type="number"
  min="1"
  placeholder="Enter amount"
  value={formData.promoAmount === 0 ? '' : formData.promoAmount}
  onChange={handleChange}
  required
/>

        <div className="modal-actions">
          <button type="submit">Submit</button>
          <button type="button" className="close" onClick={onClose}>Cancel</button>
        </div>
      </form>
    </div>
  );
};

export default PromoModal;

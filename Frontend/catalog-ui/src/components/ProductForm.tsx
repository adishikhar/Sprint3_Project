import React, { useState } from 'react';
import axios from 'axios';
import { ProductRequest } from '../types';
import './ProductForm.css';

interface Props {
  onSuccess: () => void;
}

const ProductForm: React.FC<Props> = ({ onSuccess }) => {
  const [formData, setFormData] = useState<ProductRequest>({
    productName: '',
    sku: '',
    categoryName: '',
    size: '',
    color: '',
    price: 0,
    inventoryCount: 1,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: name === 'price' || name === 'inventoryCount' ? +value : value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await axios.post('http://localhost:8080/api/products', formData);
      setFormData({
        productName: '',
        sku: '',
        categoryName: '',
        size: '',
        color: '',
        price: 0,
        inventoryCount: 1,
      });
      onSuccess();
    } catch (err) {
      console.error('Error adding product:', err);
    }
  };

  return (
    <form className="product-form" onSubmit={handleSubmit}>
      <input name="productName" value={formData.productName} onChange={handleChange} placeholder="Product Name" required />
      <input name="sku" value={formData.sku} onChange={handleChange} placeholder="SKU" required />

      <select name="categoryName" value={formData.categoryName} onChange={handleChange} required>
        <option value="">Select Category</option>
        <option value="Mens">Mens</option>
        <option value="Womens">Womens</option>
        <option value="Child">Child</option>
      </select>

      <select name="size" value={formData.size} onChange={handleChange} required>
        <option value="">Select Size</option>
        <option value="S">S</option>
        <option value="M">M</option>
        <option value="L">L</option>
        <option value="XL">XL</option>
      </select>

      <input name="color" value={formData.color} onChange={handleChange} placeholder="Color" required />
      <input
  type="number"
  name="price"
  min="0"
  value={formData.price === 0 ? '' : formData.price}
  onChange={handleChange}
  placeholder="Price"
  required
/>

<input
  type="number"
  name="inventoryCount"
  min="1"
  value={formData.inventoryCount === 1 ? '' : formData.inventoryCount}
  onChange={handleChange}
  placeholder="Inventory count"
  required
/>

      <button type="submit">Add Product</button>
    </form>
  );
};

export default ProductForm;

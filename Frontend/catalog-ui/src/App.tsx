import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import { ProductResponse, PromoResponse } from './types';
import ProductForm from './components/ProductForm';
import ProductTable from './components/ProductTable';
import PromoModal from './components/PromoModal';
import PromoTable from './components/PromoTable';
import Sidebar from './components/Sidebar';

function App() {
  const [products, setProducts] = useState<ProductResponse[]>([]);
  const [promos, setPromos] = useState<PromoResponse[]>([]);
  const [showPromoModal, setShowPromoModal] = useState(false);

  const fetchProducts = async () => {
    const res = await axios.get<ProductResponse[]>('http://localhost:8080/api/products');
    setProducts(res.data);
  };

  const fetchPromos = async () => {
    const res = await axios.get<PromoResponse[]>('http://localhost:8080/api/promotions');
    setPromos(res.data);
  };

  useEffect(() => {
    fetchProducts();
    fetchPromos();
  }, []);

  return (
    <div className="app-layout">
      <Sidebar />
      <div className="main-content">
        <h1>Catalog Dashboard</h1>
        <ProductForm onSuccess={fetchProducts} />
        <ProductTable products={products} onRefresh={fetchProducts} />
        <button className="create-promo-btn" onClick={() => setShowPromoModal(true)}>
          Create Promo Code
        </button>
        <PromoModal visible={showPromoModal} onClose={() => setShowPromoModal(false)} onSuccess={fetchPromos} />
        <PromoTable promos={promos} />
      </div>
    </div>
  );
}

export default App;

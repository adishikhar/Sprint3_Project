import React from 'react';
import './Sidebar.css';

const Sidebar: React.FC = () => {
  return (
    <div className="sidebar">
      <h2>Catalog<br />Management</h2>
      <ul>
        <li><span role="img" aria-label="dashboard">📊</span> Dashboard</li>
        <li><span role="img" aria-label="product">📦</span> Add Product</li>
        <li><span role="img" aria-label="promo">🎟️</span> Create Promo Code</li>
      </ul>
    </div>
  );
};

export default Sidebar;

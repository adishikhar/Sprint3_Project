import React from 'react';
import './Sidebar.css';

const Sidebar: React.FC = () => {
  return (
    <div className="sidebar">
      <h2>Catalog<br />Management</h2>
      <ul>
        <li>Dashboard</li>
        <li>Add Product</li>
        <li>Create Promo Code</li>
      </ul>
    </div>
  );
};

export default Sidebar;

import React from 'react';
import { PromoResponse } from '../types';
import './PromoTable.css';

interface Props {
  promos: PromoResponse[];
}

const PromoTable: React.FC<Props> = ({ promos }) => {
  return (
    <div className="promo-table-wrapper">
      <h2>All Promo Codes</h2>
      <table className="promo-table">
        <thead>
          <tr>
            <th>Promo Type</th>
            <th>Description</th>
            <th>Promo Code</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {promos.map((promo, index) => (
            <tr key={index}>
              <td>{promo.promoType}</td>
              <td>{promo.description}</td>
              <td>{promo.promoCode}</td>
              <td>${promo.promoAmount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PromoTable;

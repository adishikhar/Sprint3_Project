import React, { useState } from 'react';
import axios from 'axios';
import { ProductResponse, ProductUpdateRequest, ProductDeleteRequest } from '../types';
import './ProductTable.css';

interface Props {
  products: ProductResponse[];
  onRefresh: () => void;
}

const ProductTable: React.FC<Props> = ({ products, onRefresh }) => {
  const [editIndex, setEditIndex] = useState<number | null>(null);
  const [editData, setEditData] = useState<ProductResponse | null>(null);

  const startEdit = (index: number, product: ProductResponse) => {
    setEditIndex(index);
    setEditData(product);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    if (editData) {
      setEditData({ ...editData, [name]: name === 'price' || name === 'inventoryCount' ? +value : value });
    }
  };

  const saveEdit = async () => {
    if (!editData) return;

    const payload: ProductUpdateRequest = {
      originalProductId: products[editIndex!].productId,
      originalCategoryId: products[editIndex!].categoryId,
      originalSku: products[editIndex!].sku,

      newProductName: editData.productName,
      newCategoryName: editData.categoryName,
      newSku: editData.sku,
      newSize: editData.size,
      newColor: editData.color,
      newPrice: editData.price,
      newInventoryCount: editData.inventoryCount,
    };

    await axios.put('http://localhost:8080/api/products', payload);
    setEditIndex(null);
    setEditData(null);
    onRefresh();
  };

  const deleteProduct = async (product: ProductResponse) => {
    const payload: ProductDeleteRequest = {
      productId: product.productId,
      categoryId: product.categoryId,
      sku: product.sku,
    };

    await axios.delete('http://localhost:8080/api/products', { data: payload });
    onRefresh();
  };

  return (
    <table className="product-table">
      <thead>
        <tr>
          <th>Category</th>
          <th>Product</th>
          <th>SKU</th>
          <th>Size</th>
          <th>Color</th>
          <th>Price</th>
          <th>Inventory Count</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {products.map((prod, idx) => (
          <tr key={prod.sku}>
            {editIndex === idx ? (
              <>
                <td><input name="categoryName" value={editData?.categoryName} onChange={handleChange} /></td>
                <td><input name="productName" value={editData?.productName} onChange={handleChange} /></td>
                <td><input name="sku" value={editData?.sku} onChange={handleChange} /></td>
                <td>
                  <select name="size" value={editData?.size} onChange={handleChange}>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                  </select>
                </td>
                <td><input name="color" value={editData?.color} onChange={handleChange} /></td>
                <td><input name="price" type="number" value={editData?.price} onChange={handleChange} /></td>
                <td><input name="inventoryCount" type="number" value={editData?.inventoryCount} onChange={handleChange} /></td>
                <td>
                  <button onClick={saveEdit}>Save</button>
                  <button onClick={() => setEditIndex(null)}>Cancel</button>
                </td>
              </>
            ) : (
              <>
                <td>{prod.categoryName}</td>
                <td>{prod.productName}</td>
                <td>{prod.sku}</td>
                <td>{prod.size}</td>
                <td>{prod.color}</td>
                <td>${prod.price}</td>
                <td>{prod.inventoryCount}</td>
                <td>
                  <button onClick={() => startEdit(idx, prod)}>Edit</button>
                  <button onClick={() => deleteProduct(prod)}>Delete</button>
                </td>
              </>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ProductTable;

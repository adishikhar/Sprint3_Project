export interface ProductRequest {
  productName: string;
  sku: string;
  categoryName: string;
  size: string;
  color: string;
  price: number;
  inventoryCount: number;
}

export interface ProductResponse {
  productName: string;
  categoryName: string;
  sku: string;
  size: string;
  color: string;
  price: number;
  inventoryCount: number;
  productId: number;
  categoryId: number;
}

export interface ProductUpdateRequest {
  originalProductId: number;
  originalCategoryId: number;
  originalSku: string;

  newProductName: string;
  newCategoryName: string;
  newSku: string;
  newSize: string;
  newColor: string;
  newPrice: number;
  newInventoryCount: number;
}

export interface ProductDeleteRequest {
  productId: number;
  categoryId: number;
  sku: string;
}

export interface PromoRequest {
  promoType: string;
  description: string;
  promoAmount: number;
}

export interface PromoResponse {
  promoType: string;
  description: string;
  promoCode: string;
  promoAmount: number;
}



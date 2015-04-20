/**
 * 
 */
package com.mycompany.basket_price.model.goods;

import java.math.BigDecimal;

import com.mycompany.basket_price.model.PricedBasketItem;

/**
 * @author colin
 *
 */
public class Bread extends PricedBasketItem{

	public Bread() {
		setItemName(this.getClass().getSimpleName());
		setPrice(new BigDecimal(0.80));
	}

}
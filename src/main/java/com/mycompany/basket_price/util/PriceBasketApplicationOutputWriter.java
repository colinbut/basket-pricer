/**
 * 
 */
package com.mycompany.basket_price.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.mycompany.basket_price.model.Receipt;
import com.mycompany.basket_price.model.SpecialOffer;
import com.mycompany.basket_price.model.SpecialOfferBuy2Get1HalfPrice;
import com.mycompany.basket_price.model.SpecialOfferDiscount;


/**
 * @author colin
 *
 */
public class PriceBasketApplicationOutputWriter {

	public static String writeOutput(Receipt receipt){
		
		BigDecimal subtotal = receipt.getSubtotal().setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal total = receipt.getTotal().setScale(2, RoundingMode.HALF_EVEN);
		
		StringBuilder specialOfferSb = new StringBuilder();
		if(receipt.getSpecialOffersApplied().size() == 0){
			specialOfferSb.append("(No offers available)");
		}
		else{
			
			for(Map.Entry<SpecialOffer, BigDecimal> entry 
					: receipt.getSpecialOffersApplied().entrySet()){
				
				if(entry.getKey() instanceof SpecialOfferDiscount){
					SpecialOfferDiscount sod = (SpecialOfferDiscount)entry.getKey();
					specialOfferSb.append(sod.getItemOnSpecialOffer().getItemName());
					specialOfferSb.append(" " + sod.getDiscount() + "% off: ");
					specialOfferSb.append("-" + ((entry.getValue().doubleValue() > 1.00) 
												? "£"+entry.getValue().setScale(2, RoundingMode.HALF_EVEN)
												: entry.getValue().setScale(2, RoundingMode.HALF_EVEN)+"p"));
					specialOfferSb.append("\n");
				}
				else if(entry.getKey() instanceof SpecialOfferBuy2Get1HalfPrice){
					
					SpecialOfferBuy2Get1HalfPrice sob2g1hp = (SpecialOfferBuy2Get1HalfPrice) entry.getKey();
					
					specialOfferSb.append("Buy 2 " + sob2g1hp.getItemOnSpecialOffer().getItemName());
					specialOfferSb.append(" get " + sob2g1hp.getHalfPriceItem()+ " half price: ");
					specialOfferSb.append("-" + ((entry.getValue().doubleValue() > 1.00) 
							? "£"+entry.getValue().setScale(2, RoundingMode.HALF_EVEN)
							: entry.getValue().setScale(2, RoundingMode.HALF_EVEN)+"p"));
					specialOfferSb.append("\n");
				}
				
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Subtotal: £" + subtotal + "\n");
		sb.append(specialOfferSb.toString());
		sb.append("Total: £" + total + "\n");
		
		return sb.toString();
		
	}
	
	
}
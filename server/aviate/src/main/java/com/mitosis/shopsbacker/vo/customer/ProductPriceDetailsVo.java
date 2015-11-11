package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.BaseVO;


	@XmlRootElement
	@XmlAccessorType(XmlAccessType.PROPERTY)
	public class ProductPriceDetailsVo extends BaseVO{
		private Long priceId;
		private Long productId;
		private String measurementSize;
		private double price;
		private double tax;

		public double getTax() {
			return tax;
		}
		public void setTax(double tax) {
			this.tax = tax;
		}
		public Long getPriceId() {
			return priceId;
		}
		public void setPriceId(Long priceId) {
			this.priceId = priceId;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public String getMeasurementSize() {
			return measurementSize;
		}
		public void setMeasurementSize(String measurementSize) {
			this.measurementSize = measurementSize;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
}

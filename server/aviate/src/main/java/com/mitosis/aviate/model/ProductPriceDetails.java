package com.mitosis.aviate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.aviate.model.service.ResponseModel;


	@XmlRootElement
	@Entity
	@Table(name = "product_price_details")
	public class ProductPriceDetails extends ResponseModel{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="price_id")
		private Long priceId;
		@Column(name="product_id")
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

--
-- DATE  --------       Created By   --------------   Description --------- 
-- 
-- 20/11/2015 -----------  Fayaz  -----------  ProductyType store null changes ------- 
--
ALTER TABLE `shopsbacker`.`product_type` 
DROP FOREIGN KEY `PROD_TYPE_STORE`;
ALTER TABLE `shopsbacker`.`product_type` 
CHANGE COLUMN `STORE_ID` `STORE_ID` VARCHAR(32) NULL ;
ALTER TABLE `shopsbacker`.`product_type` 
ADD CONSTRAINT `PROD_TYPE_STORE`
  FOREIGN KEY (`STORE_ID`)
  REFERENCES `shopsbacker`.`store` (`STORE_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  -- 21/11/2015 -----------  Fayaz  -----------  Customer Details changes ------- 
  
  ALTER TABLE `shopsbacker`.`customer` 
CHANGE COLUMN `NAME` `NAME` VARCHAR(45) NULL ,
CHANGE COLUMN `EMAIL` `EMAIL` VARCHAR(45) NULL DEFAULT NULL ,
ADD UNIQUE INDEX `PHONE_NO_UNIQUE` (`PHONE_NO` ASC);

ALTER TABLE `shopsbacker`.`customer` 
CHANGE COLUMN `PASSWORD` `PASSWORD` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `PHONE_NO` `PHONE_NO` VARCHAR(32) NULL DEFAULT NULL ;

/*added description column in product table*/
ALTER TABLE `shopsbacker`.`product` 
ADD COLUMN `DESCRIPTION` VARCHAR(250) NULL;

-- Added by Kartheeswaran --
  
  CREATE TABLE `shopsbacker`.`password_reset_request` (
  `REQUEST_ID` VARCHAR(32) NOT NULL,
  `TOKEN_ID` VARCHAR(32) NULL,
  `USER_ID` VARCHAR(32) NULL,
  `USER_TYPE` VARCHAR(20) NULL,
  `CREATEDBY` VARCHAR(32) NOT NULL,
  `UPDATEDBY` VARCHAR(32) NOT NULL,
  `CREATED` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ISACTIVE` CHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`REQUEST_ID`));
  
  --
  
 -- 24/11/2015 -----------  Riyaz  -----------  isactive is chnaged string to char  product_image -------  
 ALTER TABLE `shopsbacker`.`product_image` 
CHANGE COLUMN `ISACTIVE` `ISACTIVE` CHAR NOT NULL DEFAULT 'Y' COMMENT '' ;

 -- 24/11/2015 -----------  Srini  -----------  Merchant ForeignKey Added in SalesOrder Table -------  
ALTER TABLE `shopsbacker`.`sales_order` 
ADD INDEX `FK_SO_MERCHANT_idx` (`MERCHANT_ID` ASC)  COMMENT '';
ALTER TABLE `shopsbacker`.`sales_order` 
ADD CONSTRAINT `FK_SO_MERCHANT`
  FOREIGN KEY (`MERCHANT_ID`)
  REFERENCES `shopsbacker`.`merchant` (`MERCHANT_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  -- prabakaran ---------------------------- Sales order column name changes  --------------------
  
  ALTER TABLE `shopsbacker`.`sales_order` 
CHANGE COLUMN `DELIVERY _DATE` `DELIVERY_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
CHANGE COLUMN `DELIVERY _TIME_SLOT_ID` `DELIVERY_TIME_SLOT` VARCHAR(60) NOT NULL ;


ALTER TABLE `shopsbacker`.`sales_order_line` 
DROP FOREIGN KEY `FK_SOL_SALES_ORDER`;
ALTER TABLE `shopsbacker`.`sales_order_line` 
ADD INDEX `FK_SOL_SALES_ORDER_idx` (`SALES_ORDER_ID` ASC);
ALTER TABLE `shopsbacker`.`sales_order_line` 
ADD CONSTRAINT `FK_SOL_SALES_ORDER`
  FOREIGN KEY (`SALES_ORDER_ID`)
  REFERENCES `shopsbacker`.`sales_order` (`SALES_ORDER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


  
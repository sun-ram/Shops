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
  
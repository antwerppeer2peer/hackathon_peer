package netgloo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.models.BNPPFAccountDetail;
import netgloo.models.BNPPFAccountDetailDao;

@Component
public class BNPPFAPIServiceProvider {
	
	public static final Logger log = LoggerFactory.getLogger(BNPPFAPIServiceProvider.class);
	
	@Autowired
	BNPPFAccountDetailDao bnppfAccountDetailDao;
	
	public void addAccountDetails() {
		log.info("----Add Account Details---");
		
		BNPPFAccountDetail bnppfAccountDetail = new BNPPFAccountDetail();
		bnppfAccountDetail.setAccountNumber("222789456123");
		bnppfAccountDetail.setAccountIBAN("BNP222789456123");
		bnppfAccountDetail.setBalance(1000);
		bnppfAccountDetail.setName("Dhanan Jeyan");
		bnppfAccountDetail.setMobileNumber("+32465270695");
		bnppfAccountDetailDao.save(bnppfAccountDetail);
		
		BNPPFAccountDetail bnppfAccountDetail2 = new BNPPFAccountDetail();
		bnppfAccountDetail2.setAccountNumber("222333789456");
		bnppfAccountDetail2.setAccountIBAN("BNP222333789456");
		bnppfAccountDetail2.setBalance(100);
		bnppfAccountDetail2.setName("Siby");
		bnppfAccountDetail2.setMobileNumber("+32465144735");
		bnppfAccountDetailDao.save(bnppfAccountDetail2);
	}
	
	/**
	 * 
	 * @return
	 */
	public BNPPFAccountDetail getAccountDetail(ExternalAPIInputData externalAPIInputData) {
		
		BNPPFAccountDetail BNPPFAccountDetail = null;
		
		Iterable<BNPPFAccountDetail> listOfBnppfAccountDetail = bnppfAccountDetailDao.findAll();
		
		for(BNPPFAccountDetail bnppfAccountDetail : listOfBnppfAccountDetail) {
			
			if(bnppfAccountDetail.getAccountNumber().equalsIgnoreCase(externalAPIInputData.getAccountNumber()) &&
					bnppfAccountDetail.getAccountIBAN().equalsIgnoreCase(externalAPIInputData.getIban())) {
				return bnppfAccountDetail;	
			}
		}
		
		return BNPPFAccountDetail;
	}
}

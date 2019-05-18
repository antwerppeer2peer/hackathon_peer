package netgloo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.models.INGAccountDetail;
import netgloo.models.INGAccountDetailDao;

@Component
public class INGAPIServiceProvider {
	
	public static final Logger log = LoggerFactory.getLogger(INGAPIServiceProvider.class);
	
	@Autowired
	INGAccountDetailDao ingAccountDetailDao;
	
	public void addAccountDetails() {
		log.info("----Add Account Details---");
		
		INGAccountDetail ingAccountDetail = new INGAccountDetail();
		ingAccountDetail.setAccountNumber("111789456123");
		ingAccountDetail.setAccountIBAN("ING111789456123");
		ingAccountDetail.setBalance(1000);
		ingAccountDetail.setName("Hardik Korat");
		ingAccountDetail.setMobileNumber("+32465295767");
		ingAccountDetailDao.save(ingAccountDetail);
		
		INGAccountDetail ingAccountDetail2 = new INGAccountDetail();
		ingAccountDetail2.setAccountNumber("111333789466");
		ingAccountDetail2.setAccountIBAN("ING111333789466");
		ingAccountDetail2.setBalance(100);
		ingAccountDetail2.setName("Suyash");
		ingAccountDetail2.setMobileNumber("+32467616292");
		ingAccountDetailDao.save(ingAccountDetail2);
	}
	
	/**
	 * 
	 * @return
	 */
	public INGAccountDetail getAccountDetail(ExternalAPIInputData externalAPIInputData) {
		
		INGAccountDetail INGAccountDetail = null;
		
		Iterable<INGAccountDetail> listOfINGAccounts = ingAccountDetailDao.findAll();
		
		for(INGAccountDetail ingAccountDetail : listOfINGAccounts) {
			
			if(ingAccountDetail.getAccountNumber().equalsIgnoreCase(externalAPIInputData.getAccountNumber()) &&
					ingAccountDetail.getAccountIBAN().equalsIgnoreCase(externalAPIInputData.getIban())) {
				return ingAccountDetail;	
			}
		}
		return INGAccountDetail;
	}
}

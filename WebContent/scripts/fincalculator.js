
	// Main Function to be called
	function doInterestConversion(oldInterestType,oldInterestRate,newContractPaymentType,newInterestType,loan,tenor) {
			
		if(oldInterestType == 'INTEF') {
			return doConversionFromINTEF(oldInterestRate,
				newContractPaymentType, newInterestType, loan, tenor);		
		}
		else if(oldInterestType == 'INTFL') {
			return doConversionFromINTFL(oldInterestRate,
				newContractPaymentType, newInterestType, loan, tenor);		
		}
		else if(oldInterestType == 'INTDW') {
			return doConversionFromINTDW(oldInterestRate,
				newContractPaymentType, newInterestType, loan, tenor);		
		}

	}

	function doConversionFromINTEF(
		oldInterestRate, 
		newContractPaymentType, 
		newInterestType,
		loan, 
		tenor
	) {
		if(newInterestType == 'INTEF') {
			return oldInterestRate;
		}
		else if(newInterestType == 'INTFL') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTEF_INTFL_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTEF_INTFL_CPTIA(oldInterestRate, loan, tenor);
		}
		else if(newInterestType == 'INTDW') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTEF_INTDW_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTEF_INTDW_CPTIA(oldInterestRate, loan, tenor);
		}
	}

	function doConversionFromINTFL(
		oldInterestRate, 
		newContractPaymentType, 
		newInterestType,
		loan, 
		tenor
	) {
		if(newInterestType == 'INTEF') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTFL_INTEF_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTFL_INTEF_CPTIA(oldInterestRate, loan, tenor);
		}
		else if(newInterestType == 'INTFL') {
			return oldInterestRate;
		}
		else if(newInterestType == 'INTDW') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTFL_INTDW_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTFL_INTDW_CPTIA(oldInterestRate, loan, tenor);
		}
	}

	function doConversionFromINTDW(
		oldInterestRate, 
		newContractPaymentType, 
		newInterestType,
		loan, 
		tenor
	) {
		if(newInterestType == 'INTEF') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTDW_INTEF_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTDW_INTEF_CPTIA(oldInterestRate, loan, tenor);
		}
		else if(newInterestType == 'INTFL') {
			if(newContractPaymentType == 'CPTIR')
				return doConversionFromINTDW_INTFL_CPTIR(oldInterestRate, loan, tenor);
			else if(newContractPaymentType == 'CPTIA')
				return doConversionFromINTDW_INTFL_CPTIA(oldInterestRate, loan, tenor);
		}
		else if(newInterestType == 'INTDW') {
			return oldInterestRate;
		}
	}
		
	function doConversionFromINTFL_INTDW_CPTIR (oldInterestRate, loan, tenor) {
		return convertFlat2Menurun(oldInterestRate, tenor);
	}

	function doConversionFromINTFL_INTDW_CPTIA(oldInterestRate, loan, tenor) {
		return convertFlat2MenurunInAdvance(oldInterestRate, tenor);
	}

	function doConversionFromINTDW_INTFL_CPTIR (oldInterestRate, loan, tenor) {
		return convertMenurun2Flat(oldInterestRate, tenor);
	}

	function doConversionFromINTDW_INTFL_CPTIA (oldInterestRate, loan, tenor) {
		return convertMenurun2FlatInAdvance(oldInterestRate, tenor);
	}
	
	function doConversionFromINTFL_INTEF_CPTIR (oldInterestRate, loan, tenor) {
		var paymentINTFL = calculateFlatPayment(loan, oldInterestRate, tenor);
		return calculateEfektifRate(loan, paymentINTFL, tenor);
	}

	function doConversionFromINTFL_INTEF_CPTIA (oldInterestRate, loan, tenor) {
		var paymentINTFL = calculateFlatPayment(loan, oldInterestRate, tenor);
		return calculateEfektifRateInAdvance(loan, paymentINTFL, tenor);
	}

	function doConversionFromINTEF_INTFL_CPTIR (oldInterestRate, loan, tenor) {
		var paymentINTEF = calculateEfekPayment(loan, oldInterestRate, tenor);
		return calculateFlatRate(loan, paymentINTEF, tenor);
	}

	function doConversionFromINTEF_INTFL_CPTIA (oldInterestRate, loan, tenor) {
		var paymentINTEF = calculateEfekPaymentInAdvance(loan, oldInterestRate, tenor);
		return calculateFlatRate(loan, paymentINTEF, tenor);
	}

	function doConversionFromINTEF_INTDW_CPTIR (oldInterestRate, loan, tenor) {
		var interestINTFL = doConversionFromINTEF_INTFL_CPTIR (oldInterestRate, loan, tenor);
		return doConversionFromINTFL_INTDW_CPTIR (interestINTFL, loan, tenor);
	}

	function doConversionFromINTEF_INTDW_CPTIA (oldInterestRate, loan, tenor) {
		var interestINTFL = doConversionFromINTEF_INTFL_CPTIA (oldInterestRate, loan, tenor);
		return doConversionFromINTFL_INTDW_CPTIA (interestINTFL, loan, tenor);
	}

	function doConversionFromINTDW_INTEF_CPTIR (oldInterestRate, loan, tenor) {
		var interestINTFL = doConversionFromINTDW_INTFL_CPTIR (oldInterestRate, loan, tenor);
		return doConversionFromINTFL_INTEF_CPTIR (interestINTFL, loan, tenor);
	}

	function doConversionFromINTDW_INTEF_CPTIA (oldInterestRate, loan, tenor) {
		var interestINTFL = doConversionFromINTDW_INTFL_CPTIA (oldInterestRate, loan, tenor);
		return doConversionFromINTFL_INTEF_CPTIA (interestINTFL, loan, tenor);
	}

	function binomial(a,n) {

		if (n < 0)
			return 1.0 / binomial(a, -n);

		var sum = 1.0;
		var pow = n;
		var term = 1.0;
		var cof = 1.0;
		var i;
		for (i = 1; i < 10; i++) {
			cof = cof * pow / i;
			pow = pow - 1.0;
			term = term * a;
			sum = sum + cof * term;
		}
		return sum;
	}

	function error(loan, interest, tenor, payment) {
		return loan - payment * (1.0 - binomial((interest/12.0), -tenor)) / (interest / 12.0);
	}
	
	function calculateEfektifRate(loan, payment, tenor) {
		var low=0.001; 
		var high=10.0; 
		var mid=0; 
		var err_mid;
		var count;
		var i;
		
		for (i=0; i<30; i++) {
			mid = (high + low) / 2.0;
			err_mid = error(loan, mid, tenor, payment);
			if (err_mid == 0.0) break;
			if (err_mid < 0.0) {
				low = mid;
				continue;
			}
			if (0.0 < err_mid) {
				high = mid;
				continue;
			}
		}
		mid = mid * 100.0;
		return mid;
	}
	
	function calculateEfektifRateInAdvance(loan, payment, tenor) {
		var low=0.001; 
		var high=10.0; 
		var mid=0; 
		var err_mid;
		var count;
		var i;

		for (i=0; i<30; i++) {
			mid = (high + low) / 2.0;
			err_mid = error(loan-payment, mid, tenor-1, payment);
			if (err_mid == 0.0) break;
			if (err_mid < 0.0) {
				low = mid;
				continue;
			}
			if (0.0 < err_mid) {
				high = mid;
				continue;
			}
		}
		mid = mid * 100.0;
		return mid;
	}
	
	function calculateFlatRate(loan, payment, tenor) {
		return ((payment-(loan/tenor))/loan)*12.0;
	}
	
	function calculateEfekPayment(loan, interest, tenor) {
		return loan * ( (interest/12.0) / (1.0 - Math.pow(1.0 + (interest/12.0), -tenor)));
	}
	
	function calculateEfekPaymentInAdvance(loan, interest, tenor) {
		var c = (interest/12.0) / (1 - Math.pow(1 + (interest/12.0), -(tenor-1)));
		return (loan * c) / (1.0+c);
	}

	function calculateFlatPayment(loan, interest, tenor) {
		var loan_ = parseFloat(loan);
		var A = loan_*tenor*((interest/100.0)/12.0);
		var B = loan_ + A;
		var C = B/tenor;
		return C;
	}
	
	function convertFlat2Menurun(interest, tenor) {
		return (interest * 2.0 * tenor) / (tenor+1.0);
	}
	
	function convertFlat2MenurunInAdvance(interest, tenor) {
		return (interest * 2.0 * tenor) / (tenor-1.0);
	}
	
	function convertMenurun2Flat(interest, tenor) {
		return (tenor+1.0) / (2.0 * tenor) * interest;
	}
	
	function convertMenurun2FlatInAdvance(interest, tenor) {
		return (tenor-1.0) / (2.0 * tenor) * interest;
	}
	
	// Get Difference between The Old and New Interest
	function diffInterestRate(oldInterestRate, newInterestRate)	{
		return (newInterestRate - oldInterestRate);
	}

	function diffInterest(oldInterestAmount, newInterestAmount)	{
		return (newInterestAmount - oldInterestAmount);
	}

	function calculateTotalInterestAmount(loan, interest, tenor) {
		return (loan*tenor*((interest/100.0)/12.0));
	}	

package calculations;

import java.math.BigDecimal;

import org.json.JSONException;

import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;

/**
 * Conversion class that handles conversions for conversion panel
 * @author Bunguiu Norales
 *
 */
public class Conversion {
	
	private final static String ACCESS_KEY = "aa9a2a02553cb1995d05394a7f7afe53";
	private String from;
	private String to;
	private BigDecimal amount;
	private CurrencyConverter converter;
	private BigDecimal result;
	
	/**
	 * class constructor
	 * @param amount
	 * @param from
	 * @param to
	 */
	public Conversion(BigDecimal amount ,String from, String to) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
	
	//gets conversion for a conversion object
	public BigDecimal convert() throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException {
		converter = new CurrencyConverterBuilder()
				.strategy(Strategy.CURRENCY_LAYER_FILESTORE)
				.accessKey(ACCESS_KEY)
				.buildConverter();
		converter.setRefreshRateSeconds(86400);
		 result = converter.convertCurrency(amount,Currency.get(from) ,Currency.get(to));
		return result ;
		
	}

}

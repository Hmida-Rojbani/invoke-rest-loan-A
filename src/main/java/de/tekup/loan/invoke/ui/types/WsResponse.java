package de.tekup.loan.invoke.ui.types;

import java.util.List;

import lombok.Data;

@Data
public class WsResponse {
	
	private boolean isEligble;
	private long approvedAmount;
	private List<String> criteriaMismatch;

}
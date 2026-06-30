package com.khushi.healthops.grpc;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import billing.BillingResponse;
import billing.BillingRequest;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	@Override
	public void createBillingAccount(BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {
		
		log.info("creatingBillingAccount request recieved {}", billingRequest.toString());
		
		BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("ACTIVE").build();
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}

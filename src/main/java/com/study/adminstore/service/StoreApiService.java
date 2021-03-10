package com.study.adminstore.service;

import com.study.adminstore.ifs.CountInterface;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.Store;
import com.study.adminstore.model.network.request.StoreApiRequest;
import com.study.adminstore.model.network.response.StoreApiResponse;
import com.study.adminstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StoreApiService implements CrudInterface<StoreApiRequest, StoreApiResponse>, CountInterface {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public ResponseEntity<StoreApiResponse> create(final StoreApiRequest req) {
        final StoreApiRequest storeApiRequest = req;

        final String account = storeApiRequest.getAccount();
        final String password = storeApiRequest.getPassword();
        final String name = storeApiRequest.getName();
        final String status = storeApiRequest.getStatus();
        final String address = storeApiRequest.getAddress();
        final String businessNumber = storeApiRequest.getBusinessNumber();
        final String ceoName = storeApiRequest.getCeoName();

        if(account.equals("") || password.equals("") || name.equals("") || status.equals("") || address.equals("") || businessNumber.equals("") || ceoName.equals("")) {
            System.out.println("data not fill");
        }

        final Store store = Store.builder()
                .account(storeApiRequest.getAccount())
                .password(storeApiRequest.getPassword())
                .name(storeApiRequest.getName())
                .status(storeApiRequest.getStatus())
                .address(storeApiRequest.getAddress())
                .businessNumber(storeApiRequest.getBusinessNumber())
                .ceoName(storeApiRequest.getCeoName())
                .createdAt(LocalDateTime.now())
                .createdBy("KSJ")
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .updatedBy("KSJ")
                .build();

        storeRepository.save(store);

        return new ResponseEntity<StoreApiResponse>(response(store), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StoreApiResponse> read(final Long id) {
        Optional<Store> optional = storeRepository.findById(id);
        return optional.map(store-> {
            return new ResponseEntity(store, HttpStatus.OK);
        })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<StoreApiResponse> update(final StoreApiRequest req) {
        StoreApiRequest storeApiRequest = req;
        Optional<Store> optional = storeRepository.findById(storeApiRequest.getId());

        return optional.map(store -> {
          store.setAccount(storeApiRequest.getAccount())
               .setName(storeApiRequest.getName())
               .setAddress(storeApiRequest.getAddress())
               .setBusinessNumber(storeApiRequest.getBusinessNumber())
               .setCeoName(storeApiRequest.getCeoName())
               .setUpdatedAt(LocalDateTime.now())
               .setUpdatedBy("KSJ");
          return store;
        }).map(newStore-> storeRepository.save(newStore))
                .map(newStore-> new ResponseEntity(newStore, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<StoreApiResponse> delete(final Long id) {
        Optional<Store> optional = storeRepository.findById(id);
        return optional.map(store->{
            storeRepository.delete(store);
            return new ResponseEntity(store, HttpStatus.OK);
        })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @Override
    public int countAll() {
        return 0;
    }

    private StoreApiResponse response(final Store store) {
        final StoreApiResponse storeApiResponse = StoreApiResponse.builder()
                .account(store.getAccount())
                .password(store.getPassword())
                .name(store.getName())
                .status(store.getStatus())
                .address(store.getAddress())
                .businessNumber(store.getBusinessNumber())
                .ceoName(store.getCeoName())
                .build();

        return storeApiResponse;
    }
}

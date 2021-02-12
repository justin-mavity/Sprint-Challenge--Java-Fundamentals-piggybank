package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController{
    private List<Coin> findCoins(List<Coin> myList, CheckCoin tester){
        List<Coin> tempList = new ArrayList<>();

        for(Coin c : myList){
            if(tester.test(c)){
                tempList.add(c);
            }
        }
        return tempList;
    }

    @Autowired
    CoinRepository coinrepos;

    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> piggyBankTotal(){
        List<Coin> myList = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(myList::add);

        double total = 0;

        myList.forEach(ele->System.out.println("" + ele.getQuantity() + " " + (ele.getQuantity()==1?ele.getName():ele.getNameplural())));
        for(Coin c : myList){
            total+=c.getValue();
        }
        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(myList, HttpStatus.OK);

    }

}

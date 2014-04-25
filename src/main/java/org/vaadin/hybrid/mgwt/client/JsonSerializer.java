package org.vaadin.hybrid.mgwt.client;

import java.util.ArrayList;
import java.util.List;

//import org.vaadin.hybrid.gwtrpcexample.client.AddressTO;
//import org.vaadin.hybrid.mgwt.client.JsonSerializer.AddressesJson.AddressTOJson;

import com.google.gwt.core.client.GWT;
import com.google.gwt.query.client.builders.JsonBuilder;

public class JsonSerializer {
//    private AddressTO convert(AddressTOJson t) {
//        return new AddressTO()
//            .setId(t.getId())
//            .setFirstName(t.getFirstName())
//            .setLastName(t.getLastName())
//            .setPhoneNumber(t.getPhoneNumber())
//            .setEmailAddress(t.getEmailAddress());
//    }
//    
//    private AddressTOJson convert(AddressTO t) {
//        return GWT.<AddressTOJson>create(AddressTOJson.class)
//            .setId(t.getId())
//            .setFirstName(t.getFirstName())
//            .setLastName(t.getLastName())
//            .setPhoneNumber(t.getPhoneNumber())
//            .setEmailAddress(t.getEmailAddress());
//    }
//    
//    public String serialize(List<AddressTO> l) {
//        AddressesJson addresses = GWT.create(AddressesJson.class);
//        List<AddressTOJson> addList = new ArrayList<AddressTOJson>();
//        for (AddressTO t : l) {
//            addList.add(convert(t));
//        }
//        addresses.setAddresses(addList);
//        return addresses.toString();
//    }
//    
//    public List<AddressTO> deserialize(String json) {
//        AddressesJson adresses = GWT.create(AddressesJson.class);
//        adresses.parse(json);
//        List<AddressTO> addList = new ArrayList<AddressTO>();
//        for (AddressTOJson t : adresses.getAddresses()) {
//            addList.add(convert(t));
//        }
//        return addList;
//    }
//    
//    public void addDeleteToQueue(AddressesJson a, QueueJson q, AddressTO t) {
//        List<Integer> n = new ArrayList<Integer>(q.getToDelete());
//        if (!n.contains(t.getId())) {
//            n.add(t.getId());
//            q.setToDelete(n);
//        }
//    }
//    
//    public void addSaveToQueue(AddressesJson a, QueueJson q, AddressTO t) {
//        AddressTOJson j = convert(t);
//        updateAddress(a.getAddresses(), j, false);
//        
//        List<AddressTOJson> n = q.getToSave();
//        if (!updateAddress(n, j, false)) {
//            n.add(j);
//        }
//        q.setToSave(n);
//    }
//
//    private boolean updateAddress(List<AddressTOJson> n, AddressTOJson j, boolean delete) {
//        for (int i = 0; i < n.size(); i++) {
//            if (n.get(i).getId() == j.getId()) {
//                if (delete) {
//                    n.remove(i);
//                } else {
//                    n.set(i, j);
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    public interface QueueJson extends JsonBuilder {
//        List<Integer> getToDelete();
//        QueueJson setToDelete(List<Integer> l);
//        List<AddressTOJson> getToSave();
//        QueueJson setToSave(List<AddressTOJson> l);
//    }
//    
//    public interface AddressesJson extends JsonBuilder {
//        List<AddressTOJson> getAddresses();
//        void setAddresses(List<AddressTOJson> l);
//        
//        public interface AddressTOJson extends JsonBuilder {
//            int getId();
//            AddressTOJson setId(int id);
//            String getFirstName();
//            AddressTOJson setFirstName(String firstName);
//            String getLastName();
//            AddressTOJson setLastName(String lastName);
//            String getPhoneNumber();
//            AddressTOJson setPhoneNumber(String phoneNumber);
//            String getEmailAddress();
//            AddressTOJson setEmailAddress(String emailAddress);
//        }        
//    }
}

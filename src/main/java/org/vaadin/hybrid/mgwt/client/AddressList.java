package org.vaadin.hybrid.mgwt.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.vaadin.hybrid.gwtrpcexample.client.AddressTO;
import org.vaadin.hybrid.gwtrpcexample.client.AddressbookService;
import org.vaadin.hybrid.gwtrpcexample.client.AddressbookServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

public class AddressList extends LayoutPanel {
    
    private CellListWithHeader<AddressTO> cellList;
    private List<AddressTO> addresses;
    
    AddressbookServiceAsync service = GWT.create(AddressbookService.class);
    AnimationHelper animationHelper;

    public AddressList(final AnimationHelper h) {
        animationHelper = h;
        
        HeaderPanel headerPanel = new HeaderPanel();
        headerPanel.setCenter("Addresses");

        HeaderButton addButton = new HeaderButton();
        addButton.setText("new");
        addButton.addTapHandler(new TapHandler() {
            public void onTap(TapEvent event) {
                forward(null);
            }
        });
        addButton.getElement().getStyle().setMarginRight(50, Unit.PX);
        headerPanel.setRightWidget(addButton);
        this.add(headerPanel);
        
        cellList = new CellListWithHeader<AddressTO>(new BasicCell<AddressTO>() {
            public String getDisplayString(AddressTO a) {
                return a.getFirstName() + " " + a.getLastName();
            }
        });
        cellList.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
            public void onCellSelected(CellSelectedEvent event) {
                forward(addresses.get(event.getIndex()));
            }
        });
        cellList.getCellList().setRound(true);
        cellList.getHeader().setText("Hey");

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(cellList);
        scrollPanel.setScrollingEnabledX(false);
        this.add(scrollPanel);
        
        service.getAddressess(new AsyncCallback<AddressTO[]>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(AddressTO[] result) {
                addresses = Arrays.asList(result);
                Collections.sort(addresses, new Comparator<AddressTO>() {
                    public int compare(AddressTO a, AddressTO b) {
                        return (a.getFirstName() + " " + a.getLastName())
                                .compareTo(b.getFirstName() + " " + b.getLastName());
                    }
                });
                
                cellList.getCellList().render(addresses);
            }
        });
    }

    private void forward(AddressTO a) {
        animationHelper.goTo(new Address(animationHelper, a == null ? new AddressTO() : a), Animation.SLIDE);
    }

}

package org.vaadin.hybrid.mgwt.client;

import org.vaadin.hybrid.gwtrpcexample.client.AddressTO;
import org.vaadin.hybrid.gwtrpcexample.client.AddressbookService;
import org.vaadin.hybrid.gwtrpcexample.client.AddressbookServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class Address extends LayoutPanel {
    
    public static class WidgetListWithHeader extends FlowPanel{
        public HTML header = new HTML();
        public WidgetList widgetList = new WidgetList();
        public WidgetListWithHeader() {
            header.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getListCss().listHeader());
            add(header);
            add(widgetList);
        }
    }
    
    AddressbookServiceAsync service = GWT.create(AddressbookService.class);
    AddressTO address;
    AnimationHelper animationHelper;

    public Address(final AnimationHelper h, AddressTO a) {
        this.address = a;
        this.animationHelper = h;
        
        HeaderPanel headerPanel = new HeaderPanel();
        headerPanel.setCenter("Address");

        HeaderButton backButton = new HeaderButton();
        backButton.setText("Back");
        backButton.addTapHandler(new TapHandler() {
            public void onTap(TapEvent event) {
                animationHelper.goTo(new AddressList(animationHelper), Animation.SLIDE_REVERSE);
            }
        });        
        headerPanel.setLeftWidget(backButton);
        backButton.setBackButton(true);
        
        WidgetListWithHeader form = new WidgetListWithHeader();
        form.header.setText("Contact Data");
        form.widgetList.setRound(true);
        
        final MTextBox fName = new MTextBox();
        fName.setText(address.getFirstName());
        final MTextBox lName = new MTextBox();
        lName.setText(address.getLastName());
        final MTextBox phone = new MPhoneNumberTextBox();
        phone.setText(address.getPhoneNumber());
        final MTextBox email = new MEmailTextBox();
        email.setText(address.getEmailAddress());
        
        Button save = new Button("Save");
        save.setConfirm(true);
        save.addTapHandler(new TapHandler() {
            public void onTap(TapEvent event) {
                address.setFirstName(fName.getText());
                address.setLastName(lName.getText());
                address.setPhoneNumber(phone.getText());
                address.setEmailAddress(email.getText());
                
                service.storeAddress(address, new AsyncCallback<Void>() {
                    public void onSuccess(Void result) {
                        back();
                    }
                    public void onFailure(Throwable caught) {
                    }
                });
            }
        });
        
        FlowPanel buttons = new FlowPanel();
        
        Button remove = new Button("Remove");
        remove.setImportant(true);
        remove.addTapHandler(new TapHandler() {
            public void onTap(TapEvent event) {
                Dialogs.confirm("Remove", "Are you sure", new ConfirmCallback() {
                    public void onOk() {
                        service.deleteAddress(address.getId(), new AsyncCallback<Void>() {
                            public void onSuccess(Void result) {
                                back();
                            }
                            public void onFailure(Throwable caught) {
                            }
                        });
                    }
                    public void onCancel() {
                    }
                });

            }
        });
        
        form.widgetList.add(new FormListEntry("Firstname", fName));
        form.widgetList.add(new FormListEntry("Lastname", lName));
        form.widgetList.add(new FormListEntry("Phone", phone));
        form.widgetList.add(new FormListEntry("Email", email));
        if (a.getId() != -1) {
            remove.setWidth("42%");
            remove.getElement().getStyle().setFloat(Float.LEFT);
            save.setWidth("42%");
            save.getElement().getStyle().setFloat(Float.LEFT);
            buttons.add(remove);
        }
        buttons.add(save);
        form.widgetList.add(buttons);

        this.add(headerPanel);
        this.add(form);
    }
    
    private void back() {
        animationHelper.goTo(new AddressList(animationHelper), Animation.SLIDE_REVERSE);
    }
}

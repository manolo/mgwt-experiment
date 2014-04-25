package org.vaadin.hybrid.mgwt.client;

import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.GQuery.window;

import org.vaadin.hybrid.gwtrpcexample.client.AddressbookService;
import org.vaadin.hybrid.gwtrpcexample.client.AddressbookServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.vaadin.addon.touchkit.gwt.client.offlinemode.OfflineMode;

public class MgwtEntry implements EntryPoint, OfflineMode {
    
    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                e.printStackTrace();
            }
        });
        
        startMgwtUI();
        startOfflineMonitor();
    }
    
    AddressbookServiceAsync service = GWT.create(AddressbookService.class);

    private void startMgwtUI() {
        MGWT.applySettings(MGWTSettings.getAppSetting());
        AnimatableDisplay display = GWT.create(AnimatableDisplay.class);
        final AnimationHelper animationHelper = new AnimationHelper(display);
        
        RootPanel.get().add(animationHelper);

        animationHelper.goTo(new AddressList(animationHelper), Animation.FLIP);
    }

    // Using gQuery/Html5 based monitor
    private void startOfflineMonitor() {
        $("body").append("<div class='off-on'/>");
        $("head").append("<style>.off-on{background: green; border: solid white 1px; border-radius: 50px; width: 30px; height: 30px; position: fixed; top: 4px; right: 4px; z-index: 1}.red{background: red}</style>");
        $(window).on("online, offline", new Function() {
            public void f() {
                boolean online = $($(window).prop("navigator")).prop("onLine", Boolean.class);
                if (online) {
                    deactivate();
                } else {
                    activate(null);
                }
            }
        }).trigger("online");
    }
    
    // OfflineMode
    private boolean active = false;
    @Override
    public void activate(ActivationEvent event) {
        $(".off-on").addClass("red");
        active = true;
    }

    @Override
    public boolean deactivate() {
        $(".off-on").removeClass("red");
        active = false;
        return active;
    }

    @Override
    public boolean isActive() {
//        return active;
        // FIXME: remove touchkit dependency
        // See OfflineModeEntrypoint.java:36
        return true;
    }
}

package com.epam.bp.autobase.gwt.client.activity;

import com.epam.bp.autobase.gwt.client.ViewFactory;
import com.epam.bp.autobase.gwt.client.event.RegisterEvent;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.LoginCheckCallback;
import com.epam.bp.autobase.gwt.client.rpc.RegisterService;
import com.epam.bp.autobase.gwt.client.ui.IndexView;
import com.epam.bp.autobase.gwt.server.AuthServiceImpl;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class IndexActivity extends AbstractActivity implements Presenter {
    private ViewFactory viewFactory;
    private IndexView indexView;

    public IndexActivity(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        indexView = viewFactory.getIndexView();
        indexView.setPresenter(this);
        panel.setWidget(indexView.asWidget());
        bind();
    }

    private void bind() {
        indexView.getHeader().getFormRegistration().getButton_modalRegister().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                IndexActivity.this.doRegister();
            }
        });
    }

    public void onModalRegisterButtonClicked() {
        viewFactory.getEventBus().fireEvent(new RegisterEvent());
    }

    @Override
    public void goTo(Place place) {
        AuthServiceImpl.App.getInstance().loginCheck(new LoginCheckCallback(viewFactory, place));
    }

    @Override
    public void goTo(Place place, UserDto user) {
        viewFactory.setUser(user);
        if (user != null) viewFactory.getPlaceController().goTo(place);
        else viewFactory.getPlaceController().goTo(new Index());
    }

    private void doRegister() {
        UserDto userDto = new UserDto()
                .setFirstname(indexView.getHeader().getFormRegistration().getInput_modalFirstname().getText())
                .setLastname(indexView.getHeader().getFormRegistration().getInput_modalLastname().getText())
                .setDob(indexView.getHeader().getFormRegistration().getInput_modalDob().getTextBox().getText())
                .setUsername(indexView.getHeader().getFormRegistration().getInput_modalUsername().getText())
                .setPassword(indexView.getHeader().getFormRegistration().getInput_modalPassword().getText())
                .setPassword_repeat(indexView.getHeader().getFormRegistration().getInput_modalPasswordRepeat().getText())
                .setEmail(indexView.getHeader().getFormRegistration().getInput_modalEmail().getText())
                .setBalance("0")
                .setRole(User.Role.CLIENT);
        RegisterService.App.getInstance().register(userDto, new AsyncCallback<UserDto>() {
            @Override
            public void onFailure(Throwable caught) {
                indexView.getHeader().getFormRegistration().getHelp_registration().setText("Registration error on server-side: "+caught.getMessage());
            }

            @Override
            public void onSuccess(UserDto result) {
                if (result != null) {
                    indexView.getHeader().getFormRegistration().getModal_registration().hide();
                    goTo(new Client("registered"), result);
                } else indexView.getHeader().getFormRegistration().getHelp_registration().setText("User registration isn't completed");
            }
        });
    }
}

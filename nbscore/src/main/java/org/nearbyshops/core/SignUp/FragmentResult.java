package org.nearbyshops.core.SignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import org.nearbyshops.core.Model.ModelRoles.User;
import org.nearbyshops.core.R;
import org.nearbyshops.core.R2;
import org.nearbyshops.core.SignUp.PrefSignUp.PrefrenceSignUp;

/**
 * Created by sumeet on 27/6/17.
 */

public class FragmentResult extends Fragment {


    @BindView(R2.id.account_credentials)
    TextView accountCredentials;

    User user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.fragment_sign_up_result, container, false);
        ButterKnife.bind(this,rootView);


        user = PrefrenceSignUp.getUser(getActivity());



//        if(getActivity() instanceof ReadWriteUser)
//        {
//            User user = ((ReadWriteUser) getActivity()).getSignUpProfile();

            if(user.getRt_registration_mode()== User.REGISTRATION_MODE_EMAIL)
            {
                accountCredentials.setText("E-mail : " + user.getEmail()
                + "\nPassword : XXXXX (Password is hidden)");
            }
            else if(user.getRt_registration_mode()== User.REGISTRATION_MODE_PHONE)
            {
                accountCredentials.setText("Phone : " + user.getPhone()
                        + "\nPassword : XXXXX (Password is hidden)");
            }

        return rootView;
    }




    @OnClick(R2.id.finish_button)
    void finishButton()
    {

        // reset preferences
        PrefrenceSignUp.saveUser(null,getActivity());

        getActivity().onBackPressed();
    }


}
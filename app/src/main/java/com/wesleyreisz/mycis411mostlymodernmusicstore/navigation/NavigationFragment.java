package com.wesleyreisz.mycis411mostlymodernmusicstore.navigation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wesleyreisz.mycis411mostlymodernmusicstore.R;
import com.wesleyreisz.mycis411mostlymodernmusicstore.account.AccountFragment;
import com.wesleyreisz.mycis411mostlymodernmusicstore.award.AwardFragment;
import com.wesleyreisz.mycis411mostlymodernmusicstore.friend.FriendFragment;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.HomeFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class NavigationFragment extends Fragment {

    public NavigationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        wireEvents(view);

        return view;
    }

    private void wireEvents(View view) {
        Button btnHome = (Button)view.findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNavigation(new HomeFragment());
            }
        });
        Button btnAccount = (Button)view.findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNavigation(new AccountFragment());
            }
        });
        Button btnAward = (Button)view.findViewById(R.id.btnAward);
        btnAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNavigation(new AwardFragment());
            }
        });
        Button btnFriend = (Button)view.findViewById(R.id.btnFriend);
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNavigation(new FriendFragment());
            }
        });
    }

    private void loadNavigation(Fragment fragment2load) {
        if(getActivity().findViewById(R.id.fragmentBody)!=null){
            getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,new NavigationFragment())
                .replace(R.id.fragmentBody,fragment2load)
                .commit();
        }else{
            getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,fragment2load)
                .commit();
        }
    }
}

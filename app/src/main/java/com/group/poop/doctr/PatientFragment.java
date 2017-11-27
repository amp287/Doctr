package com.group.poop.doctr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecycler;

    public PatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient, container, false);

        mRecycler = view.findViewById(R.id.patient_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<User> users = new ArrayList<User>();
        Calendar c1 = Calendar.getInstance();
        c1.set(2005, 1,1);
        users.add(new User("88945785", "Joe", "Example", c1.getTime(), "male", 77L, 170L));
        Calendar c2 = Calendar.getInstance();
        c2.set(2009, 6,22);
        users.add(new User("34587890", "Bob", "Smith", c2.getTime(), "male", 78L, 170L));
        Calendar c3 = Calendar.getInstance();
        c3.set(2002,3,13);
        users.add(new User("34785904", "Xi", "Jinping", c3.getTime(), "male", 73L, 170L));
        Calendar c4 = Calendar.getInstance();
        c4.set(2001,9,11);
        users.add(new User("45768349", "Barrack", "Obama", c4.getTime(), "male", 75L, 170L));
        Calendar c5 = Calendar.getInstance();
        c5.set(2000,5,7);
        users.add(new User("89036937", "Donald", "Trump", c5.getTime(), "male", 82L, 180L));
        Calendar c6 = Calendar.getInstance();
        c6.set(1998,2,9);
        users.add(new User("00000004", "Sam", "Adams", c6.getTime(), "male", 65L, 190L));
        Calendar c7 = Calendar.getInstance();
        c7.set(1988,2,9);
        users.add(new User("00003454", "John", "Johnson", c7.getTime(), "male", 88L, 188L));
        Calendar c8 = Calendar.getInstance();
        c8.set(1998,2,9);
        users.add(new User("07890004", "Bob", "Barker", c8.getTime(), "male", 74L, 174L));
        Calendar c9 = Calendar.getInstance();
        c9.set(1998,2,9);
        users.add(new User("06990004", "Bob", "Loblaw", c9.getTime(), "male", 88L, 199L));

        UserAdapter userAdapter = new UserAdapter(users);
        mRecycler.setAdapter(userAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

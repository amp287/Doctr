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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfferedAppointmentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OfferedAppointmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferedAppointmentsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecycler;

    public OfferedAppointmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment OfferedAppointmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OfferedAppointmentsFragment newInstance() {
        return new OfferedAppointmentsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offered_appointments, container, false);

        mRecycler = view.findViewById(R.id.offered_appointment_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Appointment> appointments = new ArrayList<Appointment>();

        Calendar cDate = Calendar.getInstance();
        cDate.set(2017, 12, 12);

        Calendar cStart = Calendar.getInstance();
        cStart.set(Calendar.HOUR_OF_DAY, 14);
        cStart.set(Calendar.MINUTE, 45);

        Calendar cEnd = Calendar.getInstance();
        cEnd.set(Calendar.HOUR_OF_DAY, 15);
        cEnd.set(Calendar.MINUTE, 45);

        appointments.add(new Appointment("Orlando",
                cDate.getTime(),
                cStart.getTime(),
                cEnd.getTime(),
                145L,
                "Eye Exam",
                "64789231678234689", // this is junk becareful with this test case
                "Dr. Tom Brady",
                "Looking at eyes and stuff."
        ));

        OfferedAppointmentAdapter oaa = new OfferedAppointmentAdapter(appointments);
        mRecycler.setAdapter(oaa);
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

package com.example.rishabh_pc.reckpi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link kpidet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link kpidet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class kpidet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String[] mParam1;

    private OnFragmentInteractionListener mListener;

    public kpidet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment kpidet.
     */
    // TODO: Rename and change types and number of parameters
    public static kpidet newInstance(String[] param1) {
        kpidet fragment = new kpidet();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kpidet, container, false);
        String rest = "Ret id: "+mParam1[8];
        ((TextView)v.findViewById(R.id.retid)).setText(rest);
        ((TextView)v.findViewById(R.id.lmtd)).setText(mParam1[0]);
        ((TextView)v.findViewById(R.id.mtd)).setText(mParam1[1]);
        ((TextView)v.findViewById(R.id.create)).setText(mParam1[2].substring(0, 19));
        ((TextView)v.findViewById(R.id.vlast)).setText(mParam1[3]);
        ((TextView)v.findViewById(R.id.vcurr)).setText(mParam1[4]);
        ((TextView)v.findViewById(R.id.ftd)).setText(mParam1[5]);
        ((TextView)v.findViewById(R.id.comml)).setText(mParam1[6]);
        ((TextView)v.findViewById(R.id.commc)).setText(mParam1[7]);
        return v;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

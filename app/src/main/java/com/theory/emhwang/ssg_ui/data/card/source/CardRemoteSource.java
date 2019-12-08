package com.theory.emhwang.ssg_ui.data.card.source;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theory.emhwang.ssg_ui.data.card.CardModel;

import java.util.ArrayList;
import java.util.List;

public class CardRemoteSource implements ICardSource {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("cardData");

    /**
     * Card Data를 가져온다. (임시로 API 대신 Firebase의 Realtime DB를 사용한다.)
     */
    @Override
    public void getCardData(final LoadCardDataCallback listener) {
        mRootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listener != null) {
                    final List<CardModel> list = new ArrayList<>();
                    String text = dataSnapshot.getValue(String.class);
                    for(int i = 0; i < 5; i++) {
                        list.add(new CardModel(text + i));
                    }
                    listener.onDataLoaded(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (listener != null) {
                    listener.onFailLoaded(databaseError.toException());
                }
            }
        });
    }

}

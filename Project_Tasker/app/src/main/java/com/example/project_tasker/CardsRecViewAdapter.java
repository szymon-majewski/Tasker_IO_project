package com.example.project_tasker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CardsRecViewAdapter extends RecyclerView.Adapter<CardsRecViewAdapter.ViewHolder>
{
    private ArrayList<Card> cards = new ArrayList<>();
    private Context context;
    private int parentProjectIndex;
    private int parentCategoryIndex;

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    public CardsRecViewAdapter(Context context, int parentProjectIndex, int parentCategoryIndex)
    {
        this.context = context;
        this.parentProjectIndex = parentProjectIndex;
        this.parentCategoryIndex = parentCategoryIndex;
    }

    @NonNull
    @Override
    public CardsRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cards_list_item, parent, false );

        CardsRecViewAdapter.ViewHolder holder = new CardsRecViewAdapter.ViewHolder(view);

        return holder;
    }

    public void changeBacgroundColor ( CardsRecViewAdapter.ViewHolder holder, int position ) {
        boolean cardStatus = true;

        for ( Task task: cards.get( position ).tasks ) {
            if (task.getStatus()) {
                holder.itemView.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.medium_gray));
            }

            else {
                holder.itemView.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.white));
                cardStatus = false;
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CardsRecViewAdapter.ViewHolder holder, int position) {
        holder.txtCardName.setText( cards.get( position ).getName() );
        holder.txtCardDescription.setText( cards.get( position ).getDescription() );
        changeBacgroundColor( holder, position);

        holder.cardsListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, TasksActivity.class);
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", cards.indexOf( cards.get( position ) ) );
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtCardName;
        private TextView txtCardDescription;
        private MaterialCardView cardsListItemParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCardName = itemView.findViewById(R.id.txtCardName);
            txtCardDescription = itemView.findViewById(R.id.txtCardDescription);
            cardsListItemParent = itemView.findViewById(R.id.cardsListItemParent);
        }
    }
}

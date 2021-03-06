package com.example.project_tasker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CategoriesRecViewAdapter extends RecyclerView.Adapter<CategoriesRecViewAdapter.ViewHolder> {
    private ArrayList<Category> categories = new ArrayList<>();
    private Context context;
    private int parentProjectIndex;

    public void setCategories( ArrayList< Category > categories ) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public CategoriesRecViewAdapter(Context context, int parentProjectIndex)
    {
        this.context = context;
        this.parentProjectIndex = parentProjectIndex;
    }

    @NonNull
    @Override
    public CategoriesRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.categories_list_item, parent, false );

        CategoriesRecViewAdapter.ViewHolder holder = new CategoriesRecViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRecViewAdapter.ViewHolder holder, int position) {
        holder.txtCategoryName.setText( categories.get( position ).getName() );
        holder.txtCategoryDescription.setText( categories.get( position ).getDescription() );
        holder.frameLayout.setBackground( new ColorDrawable( categories.get(position).getColor() ) );

        holder.categoriesListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, CardsActivity.class);
                intent.putExtra( "parentCategoryIndex", categories.indexOf( categories.get( position ) ) );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtCategoryName;
        private TextView txtCategoryDescription;
        private MaterialCardView categoriesListItemParent;
        private FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtCategoryDescription = itemView.findViewById(R.id.txtCategoryDescription);
            categoriesListItemParent = itemView.findViewById(R.id.categoriesListItemParent);
            frameLayout = itemView.findViewById(R.id.frame);
        }
    }
}

spackage com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.models.BakingModel;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private Context dContext;
    //private ArrayList<ExampleItem> mExampleList;
   // private OnItemClickListener mListener;

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        public TextView dTextIngredients;
        public ArrayList<BakingModel.Steps> dStepsList;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            dTextIngredients =
        }
    }



    @NonNull
    @Override
    public DetailAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter detailAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package es.cice.toolbartest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.cice.toolbartest.R;
import es.cice.toolbartest.model.Car;

/**
 * Created by cice on 20/1/17.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> implements Filterable {

    private List<Car> carList;
    private Context ctx;
    public CarAdapter(Context ctx, List<Car> list){
        carList=list;
        this.ctx=ctx;
    }
    /*
        Este método se llama cada vez que sea necesario construir una nueva fila

      */


    @Override
    //para crear un fila nueva
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Obtenemos el inflater necesario para construir una fila definida en xml
    //el inflater asociado a ese contexto
        LayoutInflater inflater=LayoutInflater.from(ctx);
        //construimos la fila
        View row = inflater.inflate(R.layout.car_row, parent, false);
        ViewHolder holder= new ViewHolder(row);
        return holder;
    }


    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, int position) {
        //se recicla la fila
        holder.carIV.setImageResource(carList.get(position).getMiniatura());
        holder.carTV.setText(carList.get(position).getFabricante() + " " +
        carList.get(position).getModelo());

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return carList.size();
    }


    @Override
    //los adaptadores filtrables tienen asociado un filtro. Con él podemos establecer restricciones a las fuentees e datos
    public Filter getFilter() {

        return new CarFilter();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //además de guardar la fila, para minimizar el tiempo para reciclar una fila mantiene las referencias de los widgets que hay q modificar
        //el imageview y el textview
        private ImageView carIV;
        private TextView carTV;

        public ViewHolder(View itemView){
            super(itemView);
            carIV = (ImageView)itemView.findViewById(R.id.carThumbIV);
            carTV = (TextView)itemView.findViewById(R.id.carNameTV);
            carIV.setOnClickListener(new View.OnClickListener() {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    Log.d("CardViewHolder", "old position: "+ getOldPosition());
                    Log.d("CardViewHolder", "layout position: " + getLayoutPosition());
                    Log.d("CardViewHolder", "adapter position: " + getAdapterPosition());
                   /* Intent intent=new Intent(ctx, CarDetailActivity.class);
                    intent.putExtra(CarDetailActivity.IMAGEN_EXTRA,
                            carList.get(getAdapterPosition()).getImagen());
                    intent.putExtra(CarDetailActivity.DESCRIPCION_EXTRA,
                            carList.get(getAdapterPosition()).getDescripcion());
                    intent.putExtra(CarDetailActivity.FABRICANTE_EXTRA,
                            carList.get(getAdapterPosition()).getFabricante());
                    intent.putExtra(CarDetailActivity.MODELO_EXTRA,
                            carList.get(getAdapterPosition()).getModelo());
                    ctx.startActivity(intent);*/

                }//onClick
            });
        }
    }
    //clase interna que hace el filtro
    public class CarFilter extends Filter{
        public static final String TAG="CarFilter";
        private List<Car> originalList;
        private List<Car> filteredList;
        public CarFilter(){
            originalList=new LinkedList<>(carList);
            filteredList=new ArrayList<>();//lista vacía

        }
        @Override
        protected FilterResults performFiltering(CharSequence filterData) {
            Log.d(TAG, "performFiltering....");
            String filterStr=filterData.toString();
            FilterResults results = new FilterResults();
            filteredList=new ArrayList<>();
            for(Car car:originalList){
                if(car.getFabricante().equalsIgnoreCase(filterStr) ||
                        car.getModelo().equalsIgnoreCase(filterStr)){
                    filteredList.add(car);
                }
            }
            results.values=filteredList;
            results.count=filteredList.size();

            return results;
        }


        @Override
        //aquí definimos cómo modificamos el adaptador a partir de los datos filtrados
        protected void publishResults(CharSequence constraint, FilterResults results) {
            carList=(List<Car>) results.values;
            //List <Car> list=(List<Car>) results.values;
            //if (list.size()==0)

            notifyDataSetChanged();


        }
    }
}

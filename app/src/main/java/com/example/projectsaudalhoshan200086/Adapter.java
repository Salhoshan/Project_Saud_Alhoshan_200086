package com.example.projectsaudalhoshan200086;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    ArrayList<Student> list;

    public Adapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Student student = list.get(position);
        holder.id.setText(student.getId());
        holder.name.setText(student.getName());
        holder.surname.setText(student.getSurname());
        holder.father.setText(student.getFatherName());
        holder.dob.setText(student.getDob());
        holder.national.setText(student.getNationalID());
        holder.gender.setText(student.getGender());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, surname, father, dob, national, gender;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.listID);
        name = itemView.findViewById(R.id.listName);
        surname = itemView.findViewById(R.id.listSur);
        father = itemView.findViewById(R.id.listFather);
        dob = itemView.findViewById(R.id.listDOB);
        national = itemView.findViewById(R.id.listNational);
        gender = itemView.findViewById(R.id.listGender);
    }
}
}

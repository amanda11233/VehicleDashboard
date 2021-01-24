package com.example.classwork3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classwork3.R;
import com.example.classwork3.models.Students;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StuHolder> {

    Context context;
    List<Students> studentsList;

    public StudentsAdapter(Context context, List<Students> studentsList){
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public StudentsAdapter.StuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.students_layout, null);
        StuHolder holder = new StuHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StuHolder holder, int position) {
        Students students = studentsList.get(position);
        holder.name.setText(students.getName());
        holder.email.setText(students.getEmail());
        holder.phone.setText(students.getPhone());


    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StuHolder extends RecyclerView.ViewHolder{

        TextView name, email, phone;

        public StuHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.stuname);
            email = itemView.findViewById(R.id.stuemail);
            phone = itemView.findViewById(R.id.stuphone);

        }
    }
}

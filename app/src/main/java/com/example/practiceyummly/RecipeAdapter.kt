package com.example.practiceyummly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(val context: Context, var list:MutableList<Receipe>):RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
     val view:View=LayoutInflater.from(context).inflate(R.layout.recipe_layout,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       val model=list.get(position)
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateData(modelList: MutableList<Receipe>) {
        list= modelList
        notifyDataSetChanged()
    }


    class RecipeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var image:ImageView = itemView.findViewById(R.id.recipeImage)
        var recipeName:TextView
        var recipeUser:TextView
       var recipeIngredients:TextView
        var recipeTime:TextView
        var recipeCalories:TextView
        var recipeCollection:TextView

        init {
            recipeName=itemView.findViewById(R.id.recipeName)
            recipeUser=itemView.findViewById(R.id.recipeUser)
            recipeIngredients=itemView.findViewById(R.id.recipeIngredients)
            recipeTime=itemView.findViewById(R.id.recipeTime)
            recipeCalories=itemView.findViewById(R.id.recipeCalories)
            recipeCollection=itemView.findViewById(R.id.recipeColletion)
        }

        fun setData(model:Receipe){
         Glide.with(image).load(model.Images).into(image)
            recipeName.text = model.RecipeName
            recipeUser.text=model.User
            recipeIngredients.text=model.Ingredients
            recipeTime.text=model.Minutes
            recipeCalories.text=model.Calories
            recipeCollection.text=model.Collection


        }



    }
}
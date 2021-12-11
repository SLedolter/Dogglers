/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dataset: List<Dog> = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val imgProfile:ImageView = view!!.findViewById(R.id.img_profile)
        val txtName: TextView = view!!.findViewById(R.id.txt_name)
        val txtAge: TextView = view!!.findViewById(R.id.txt_age)
        val txtHobbies: TextView = view!!.findViewById(R.id.txt_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val chosenLayout:Int
        when(layout){
            Layout.VERTICAL -> chosenLayout = R.layout.vertical_horizontal_list_item
            Layout.HORIZONTAL ->  chosenLayout = R.layout.vertical_horizontal_list_item
            Layout.GRID ->  chosenLayout = R.layout.grid_list_item
            else ->  chosenLayout = R.layout.vertical_horizontal_list_item
        }

        val adapterLayout:View = LayoutInflater.from(parent.context).inflate(chosenLayout, parent, false)

        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = dataset[position]
        holder.imgProfile.setImageResource(item.imageResourceId)
        holder.txtName.text = item.name

        val resources = context?.resources
        holder.txtAge.text = resources?.getString(R.string.dog_age, item.age)
        holder.txtAge.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}

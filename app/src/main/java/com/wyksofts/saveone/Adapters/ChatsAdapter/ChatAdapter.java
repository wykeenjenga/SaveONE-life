package com.wyksofts.saveone.Adapters.ChatsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wyksofts.saveone.R;
import com.wyksofts.saveone.models.ChatModel.ChatsModel;
import com.wyksofts.saveone.models.ChatModel.DeleteMessage.DeleteMessage;
import com.wyksofts.saveone.util.AlertPopDiag;
import com.wyksofts.saveone.util.showAppToast;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatsViewHolder> {

    List<ChatsModel> list_array;
    Context context;
    FirebaseUser user;

    public ChatAdapter(List<ChatsModel> list_array, Context context) {
        this.list_array = list_array;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chats_card, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        ChatsModel data = list_array.get(position);

        //message sender
        holder.chat_name.setText(data.getUser_name());

        //message time
        holder.chat_time.setText(data.getTime());

        //message
        holder.chat_txt.setText(data.getUser_text());

        //delete message
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing the popup menu
                PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(context, R.style.PopupMenu), holder.more);

                popupMenu.getMenuInflater().inflate(R.menu.message_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch(menuItem.getItemId()) {

                            case R.id.delete_message:


                                user = FirebaseAuth.getInstance().getCurrentUser();

                                //get current user
                                if (user != null) {

                                    //check if it's her/ his message
                                    if (user.getEmail().equals(data.getUser_email())){
                                        //delete message

                                        String time = data.getDate()+data.getTime();

                                        new DeleteMessage().deleteMessage(context,time);
                                        removeMessage(data);
                                    }else{
                                        //not users message
                                        new showAppToast().showFailure(context,"You can't delete this message.");
                                    }

                                }else {
                                    //user is null
                                    new showAppToast().showFailure(context,"You are not logged in!");
                                }

                                break;
                            case R.id.reply_message:
                            case R.id.forward_message:
                                new AlertPopDiag(context).show("This feature is not support on this app update","Update");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        //get current user
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            //get current user email
            String user_mail = user.getEmail();

            //check it's current user message
            if (user_mail.equals(data.getUser_email())){

                //set background color of users message
                setCurrentUserCard(holder.chats_bg);

                //set texts colors
                holder.chat_name.setTextColor(context.getResources().getColor(R.color.blue));
                holder.chat_txt.setTextColor(context.getResources().getColor(R.color.black));
                holder.chat_time.setTextColor(context.getResources().getColor(R.color.black));

                //get screen size
                int screenWidth = getScreenWidth()/2;

                //set margin
                setCardMargins(screenWidth,0, holder.chats_bg);


            }else{
                //set background color for other users message
                setOtherUserCard(holder.chats_bg);

                //get screen size
                int screenWidth = getScreenWidth()/2;

                //set margin
                setCardMargins(0,screenWidth, holder.chats_bg);
            }
        }else{

            setOtherUserCard(holder.chats_bg);

            //get screen size
            int screenWidth = getScreenWidth()/2;

            //set margin
            setCardMargins(0,screenWidth, holder.chats_bg);
        }
    }


    //delete message
    public void removeMessage(ChatsModel message) {
        int position = list_array.indexOf(message);
        list_array.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list_array.size());
    }


    //set current user card
    @SuppressLint("UseCompatLoadingForDrawables")
    public void setCurrentUserCard(CardView view){
        view.setBackground(context.getResources().getDrawable(R.drawable.user_bg));
    }


    //set other user card
    @SuppressLint("UseCompatLoadingForDrawables")
    public void setOtherUserCard(CardView view){
        view.setBackground(context.getResources().getDrawable(R.drawable.user_bg_others));
    }


    //set margins
    public void setCardMargins(int left, int right, CardView view){
        ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams)
                view.getLayoutParams();
        cardViewMarginParams.setMargins(left, 0, right, 0);
        view.requestLayout();
    }

    //get screen width
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }


    @Override
    public int getItemCount() {

        return list_array.size();
    }

}

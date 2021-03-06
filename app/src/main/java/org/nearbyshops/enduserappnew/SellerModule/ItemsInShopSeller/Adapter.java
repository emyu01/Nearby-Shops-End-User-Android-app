package org.nearbyshops.enduserappnew.SellerModule.ItemsInShopSeller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import org.nearbyshops.core.Model.ShopItem;
import org.nearbyshops.enduserappnew.ViewHolderSeller.ViewHolderShopItemSeller;
import org.nearbyshops.enduserappnew.ViewHoldersUtility.LoadingViewHolder;
import org.nearbyshops.enduserappnew.ViewHoldersUtility.Models.EmptyScreenDataFullScreen;
import org.nearbyshops.enduserappnew.ViewHoldersUtility.Models.HeaderTitle;
import org.nearbyshops.enduserappnew.ViewHoldersUtility.ViewHolderHeader;
import org.nearbyshops.enduserappnew.ViewHoldersUtility.ViewHolderEmptyScreenFullScreen;

import java.util.List;

/**
 * Created by sumeet on 19/12/15.
 */


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Object> dataset;
    private Context context;


    public static final int VIEW_TYPE_SHOP_ITEM = 2;


    public static final int VIEW_TYPE_EMPTY_SCREEN = 5;
    public static final int VIEW_TYPE_HEADER = 3;
    public static final int VIEW_TYPE_SCROLL_PROGRESS_BAR = 4;



    private Fragment fragment;
    private boolean loadMore;




    public Adapter(List<Object> dataset, Context context, Fragment fragment) {

        this.dataset = dataset;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        if(viewType == VIEW_TYPE_SHOP_ITEM)
        {
            return ViewHolderShopItemSeller.create(parent,context,fragment,this,dataset);
        }
        else if(viewType == VIEW_TYPE_HEADER)
        {
            return ViewHolderHeader.create(parent,context);
        }
        else if(viewType == VIEW_TYPE_SCROLL_PROGRESS_BAR)
        {
            return LoadingViewHolder.create(parent,context);
        }
        else if(viewType==VIEW_TYPE_EMPTY_SCREEN)
        {
            return ViewHolderEmptyScreenFullScreen.create(parent,context);
        }


        return null;
    }






    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof ViewHolderShopItemSeller)
        {
            ((ViewHolderShopItemSeller) holder).bindShopItem((ShopItem) dataset.get(position));

        }
        else if (holder instanceof ViewHolderHeader) {

            if (dataset.get(position) instanceof HeaderTitle) {

                ((ViewHolderHeader) holder).setItem((HeaderTitle) dataset.get(position));
            }

        }
        else if(holder instanceof ViewHolderEmptyScreenFullScreen)
        {
            ((ViewHolderEmptyScreenFullScreen) holder).setItem((EmptyScreenDataFullScreen) dataset.get(position));
        }
        else if (holder instanceof LoadingViewHolder) {

            ((LoadingViewHolder) holder).setLoading(loadMore);
        }

    }









    @Override
    public int getItemViewType(int position) {

        super.getItemViewType(position);

        if(position == dataset.size())
        {
            return VIEW_TYPE_SCROLL_PROGRESS_BAR;
        }
        if (dataset.get(position) instanceof ShopItem)
        {
            return VIEW_TYPE_SHOP_ITEM;
        }
        else if(dataset.get(position) instanceof HeaderTitle)
        {
            return VIEW_TYPE_HEADER;
        }
        else if(dataset.get(position) instanceof EmptyScreenDataFullScreen)
        {
            return VIEW_TYPE_EMPTY_SCREEN;
        }

        return -1;
    }

    @Override
    public int getItemCount() {

        return (dataset.size()+1);
    }




    public void setLoadMore(boolean loadMore)
    {
        this.loadMore = loadMore;
    }


}
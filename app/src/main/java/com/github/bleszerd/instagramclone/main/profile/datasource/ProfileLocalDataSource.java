package com.github.bleszerd.instagramclone.main.profile.datasource;

import com.github.bleszerd.instagramclone.common.models.Database;
import com.github.bleszerd.instagramclone.common.models.Post;
import com.github.bleszerd.instagramclone.common.models.User;
import com.github.bleszerd.instagramclone.common.models.UserProfile;
import com.github.bleszerd.instagramclone.common.presenter.Presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * InstagramClone
 * 06/08/2021 - 13:30
 * Created by bleszerd.
 *
 * @author alive2k@programmer.net
 */
public class ProfileLocalDataSource implements ProfileDataSource{
    @Override
    public void findUser(@NotNull Presenter presenter) {
        Database db = Database.INSTANCE;
        db.findUser(db.getUserAuth().getUUID());
        db.addOnSuccessListener(user -> {
            User _user = (User) user;
            db.findPosts(_user.getUuid());
            db.addOnSuccessListener( posts -> {
                List<Post> _posts = (List<Post>) posts;
                presenter.onSuccess(new UserProfile(_user, _posts));
                presenter.onComplete();
            });
        });
    }
}

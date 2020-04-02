package com.wangj.web.italker.push.bean.db;

import com.google.common.collect.SetMultimap;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_USER")
public class User {

    //设置一个id类型
    @Id
    //这是一个主键
    @PrimaryKeyJoinColumn
    //主键生成存储类型为UUID
    @GeneratedValue(generator = "uuid")
    //把uuid的生成器定义为uuid2，uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    //不孕系更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String id;

    //用户名必须唯一
    @Column(nullable = false, length = 128, unique = true)
    private String name;

    //用户名必须唯一
    @Column(nullable = false, length = 128, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column
    private String portrait;

    //性别有初始值，所以不为空
    @Column(nullable = false)
    private int sex = 0;


    //token 可以拉去用户信息，所有token必须唯一
    @Column(unique = true)
    private String token;

    //用于推送设备id
    @Column
    private String pushId;

    //定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    //定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    //最后一次收到消息的时间
    @Column()
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

    //我关注的人的列表
    //对应的数据库表字段为TB_USER_FOLLOW.originId
    @JoinColumn(name = "originId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    //一对多
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();

    //关注我的人
    @JoinColumn(name = "targetId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    //一对多 一个用户可以被很多人关注
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> follower = new HashSet<>();

    //我所创建的群
    @JoinColumn(name = "ownerId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    //一对多 一个用户可以被很多人关注
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }

    public Set<UserFollow> getFollower() {
        return follower;
    }

    public void setFollower(Set<UserFollow> follower) {
        this.follower = follower;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }
}

package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_admin")
    private int isAdmin;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<ChatRoom> hostedChatRooms;

    @OneToMany(mappedBy = "joinedUser")
    private List<RoomMember> joinedChatRooms;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin == 0) return List.of(new SimpleGrantedAuthority("ADMIN"));
        return List.of(new SimpleGrantedAuthority("CUSTOMER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    @OneToMany(mappedBy = "member")
//    private List<RoomMember> joinedRooms;
}
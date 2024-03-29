package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Dto.JoinRoomDto;
import com.spring.hibernate.api_appchat.Dto.MemberDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import com.spring.hibernate.api_appchat.Entity.RoomMember;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {
    private final EntityManager entityManager;

    @Autowired
    public ChatRoomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ChatRoom findById(long id) {
        return entityManager.find(ChatRoom.class, id);
    }

    @Override
    public ResponseEntity<?> findByRoomName(String name) {
        String query = "FROM ChatRoom where roomName=:name";
        TypedQuery<ChatRoom> typedQuery = entityManager.createQuery(query, ChatRoom.class);
        typedQuery.setParameter("name", name);
        if (typedQuery.getResultList().isEmpty()) return new ResponseEntity<>("Room not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(typedQuery.getSingleResult(), HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> createRoom(ChatRoomDto chatRoomDto) {
        User user = entityManager.find(User.class, chatRoomDto.getHostId());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(chatRoomDto.getName());
        chatRoom.setPasswordRoom(chatRoomDto.getPasswordRoom());
        chatRoom.setHost(user);
        chatRoom.setAvatarRoom(chatRoomDto.getAvatarRoom());
        List<RoomMember> roomMembers = new ArrayList<>();
        RoomMember roomMember = new RoomMember();
        roomMember.setChatRoom(chatRoom);
        roomMember.setJoinedUser(user);
        roomMember.setJoinedAt(String.valueOf(LocalDateTime.now()));
        roomMembers.add(roomMember);
        chatRoom.setMembers(roomMembers);
        chatRoom.setCreatedAt(String.valueOf(LocalDateTime.now()));
        entityManager.persist(chatRoom);
        entityManager.persist(roomMember);
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteRoom(long roomId, long hostId) {
        ChatRoom chatRoom = findById(roomId);
        List<RoomMember> roomMembers = chatRoom.getMembers();
        if (chatRoom.getHost().getId() != hostId)
            return new ResponseEntity<>("No permission!!", HttpStatus.BAD_REQUEST);
        for (RoomMember roomMember : roomMembers) {
            entityManager.remove(roomMember);
        }
        entityManager.remove(chatRoom);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> chatRoomDetails(long roomId) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, roomId);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> editChatroom(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = findById(chatRoomDto.getId());
        chatRoom.setRoomName(chatRoomDto.getName());
        chatRoom.setPasswordRoom(chatRoomDto.getPasswordRoom());
        chatRoom.setAvatarRoom(chatRoomDto.getAvatarRoom());
        chatRoom.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> addUser(JoinRoomDto joinRoomDto) {
        ChatRoom room = entityManager.find(ChatRoom.class, joinRoomDto.getRoomId());
        if (room == null) return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        User user = entityManager.find(User.class, joinRoomDto.getUserId());
        if (user == null) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        if (joinRoomDto.getHostId() != room.getHost().getId())
            return new ResponseEntity<>("No permission", HttpStatus.BAD_REQUEST);
        RoomMember member = new RoomMember();
        member.setChatRoom(room);
        member.setJoinedUser(user);
        member.setJoinedAt(String.valueOf(LocalDateTime.now()));
        entityManager.persist(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> removeUser(JoinRoomDto joinRoomDto) {
        ChatRoom room = entityManager.find(ChatRoom.class, joinRoomDto.getRoomId());
        if (room == null) return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        User user = entityManager.find(User.class, joinRoomDto.getUserId());
        if (user == null) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        if (joinRoomDto.getHostId() != room.getHost().getId())
            return new ResponseEntity<>("No permission", HttpStatus.BAD_REQUEST);
        List<RoomMember> members = room.getMembers();
        for (RoomMember member : members) {
            if (member.getJoinedUser().getId() == joinRoomDto.getUserId()) {
                entityManager.remove(member);
                return new ResponseEntity<>("Remove user successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("The user has not joined this chat room", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> findUserByDisplayName(long roomId, String displayName) {
        List<MemberDto> result = new ArrayList<>();
        try {
            String query = "from RoomMember where joinedUser.displayName like :displayName and chatRoom.id=:roomId";
            TypedQuery<RoomMember> typedQuery = entityManager.createQuery(query, RoomMember.class);
            typedQuery.setParameter("displayName", "%" + displayName + "%");
            typedQuery.setParameter("roomId", roomId);
            List<RoomMember> roomMembers = typedQuery.getResultList();
            for(RoomMember roomMember : roomMembers){
                MemberDto memberDto = new MemberDto();
                memberDto.setId(roomMember.getId());
                memberDto.setChatRoom(roomMember.getChatRoom());
                memberDto.setJoinedUser(roomMember.getJoinedUser());
                memberDto.setJoinedAt(roomMember.getJoinedAt());
                result.add(memberDto);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred during query execution", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

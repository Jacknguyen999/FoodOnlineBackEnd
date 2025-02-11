package com.example.food.service;

import com.example.food.DTO.RestaurentDTO;
import com.example.food.model.*;
import com.example.food.repository.*;
import com.example.food.request.CreateRestaurantRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurentServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;




    @Override
    public Restaurent createRestaurent(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurent restaurent = new Restaurent();
        restaurent.setCuisineType(req.getCuisineType());
        restaurent.setAddress(address);
        restaurent.setContactInformation(req.getContactInformation());
        restaurent.setDescription(req.getDescription());
        restaurent.setImages(req.getImages());
        restaurent.setName(req.getName());
        restaurent.setOpeningHour(req.getOpeningHours());
        restaurent.setRegistrationDate(LocalDateTime.now());
        restaurent.setOwner(user);

        return restaurantRepository.save(restaurent);
    }

    @Override
    public Restaurent updateRestaurent(Long restaurentId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurent restaurent = findRestaurantById(restaurentId);

        if (restaurent == null){
            throw new Exception("Restaurent not found id " + restaurentId);
        }
        if(updatedRestaurant.getCuisineType()!= null){
            restaurent.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(updatedRestaurant.getDescription()!=null){
            restaurent.setDescription(updatedRestaurant.getDescription());
        }
        if(updatedRestaurant.getName()!=null){
            restaurent.setName(updatedRestaurant.getName());
        }
        if(updatedRestaurant.getOpeningHours()!=null){
            restaurent.setOpeningHour(updatedRestaurant.getOpeningHours());
        }
        if(updatedRestaurant.getContactInformation()!=null){
            restaurent.setContactInformation(updatedRestaurant.getContactInformation());
        }
        if(updatedRestaurant.getImages()!=null){
            restaurent.setImages(updatedRestaurant.getImages());
        }

        return restaurantRepository.save(restaurent);
    }

    @Override
    public void deleteRestaurent(Long restaurentId) throws Exception {
        Restaurent restaurent = findRestaurantById(restaurentId);

        if (restaurent == null){
            throw new Exception("Restaurent not found id " + restaurentId);
        }
        try{
            /*// Delete related IngredientItems
            ingredientItemRepository.deleteIngredientsByRestaurantId(restaurentId);

            // Delete related Foods and Orders (if needed)
            foodRepository.deleteFoodsByRestaurantId(restaurentId);
            orderRepository.deleteOrdersByRestaurantId(restaurentId);*/

            // Delete the Restaurent itself
            restaurantRepository.delete(restaurent);
        }
        catch (DataIntegrityViolationException e) {
            throw new Exception("It has associated orders or foods",e);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    @Override
    public List<Restaurent> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurent> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurent findRestaurantById(Long id) throws Exception {

        Optional<Restaurent> restaurent = restaurantRepository.findById(id);
        if (restaurent.isEmpty()) {
            throw new Exception("Restaurent not found" + id);

        }
        return restaurent.get();
    }

    @Override
    public Restaurent getRestaurantByUserId(long userId) throws Exception {
        Restaurent restaurent = restaurantRepository.findByOwnerId(userId);
        if(restaurent == null){
            throw new Exception("Restaurent not found" + userId);
        }

        return restaurent;
    }

    @Override
    public RestaurentDTO addtoFavorite(Long restaurentId, User user) throws Exception {
        Restaurent restaurent = findRestaurantById(restaurentId);

        RestaurentDTO dto = new RestaurentDTO();
        dto.setDescription(restaurent.getDescription());
        dto.setImages(restaurent.getImages());
        dto.setTitle(restaurent.getName());
        dto.setId(restaurent.getId());

//        if(user.getFavorites().contains(dto)){
//            user.getFavorites().remove(dto);
//        }
//        else user.getFavorites().add(dto);
        boolean isFavorite = false;
        List<RestaurentDTO> favorites  = user.getFavorites();
        for(RestaurentDTO favorite : favorites){
            if(favorite.getId() == restaurent.getId()){
                isFavorite = true;
                break;
            }
        }
        if(isFavorite){
            favorites.removeIf(favorite -> favorite.getId() == restaurent.getId());
        }else{
            favorites.add(dto);
        }

        userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurent updateRestaurentStatus(Long id) throws Exception {
        Restaurent restaurent = findRestaurantById(id);
        restaurent.setOpen(!restaurent.isOpen());
        return restaurantRepository.save(restaurent);
    }
}

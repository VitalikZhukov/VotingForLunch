package ru.vote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Restaurant;
import ru.vote.service.RestaurantService;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFoundWithId;
import static ru.vote.util.ValidationUtil.checkNotFound;
import static ru.vote.util.ValidationUtil.checkNew;
import static ru.vote.util.ValidationUtil.assureIdConsistent;

@Controller
public class RestaurantRestController  extends AbstractRestaurantController{

}

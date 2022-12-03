package ooga.model.entities;

import ooga.controller.JSONInformationDecoder;
import ooga.model.Model;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionChartGetter;
import ooga.model.collisions.collisionhandling.DefaultCollisionChartGetter;
import ooga.model.entities.characters.AutomaticMovingCharacter;
import ooga.model.entities.characters.maincharacters.MainCharacterEntity;
import ooga.model.entities.containers.exceptions.InvalidTypeException;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.data.Info;
import ooga.model.entities.movement.MovementQueue;

import java.lang.reflect.InvocationTargetException;

/**
 * Class that makes Entities of certain types to be added to containers.
 * Uses reflection to get from 'type' String to an Entity object
 */
public class EntityFactory {
    private JSONInformationDecoder decoder;

    public EntityFactory(JSONInformationDecoder decoder){
        this.decoder = decoder;
    }

    public AutomaticMovingCharacter makeAutomaticMover(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
        CollisionChart chart = collisionChart(type);

        MovementQueue queue = decoder.getMovementQueue(type);

        AutomaticMovingCharacter newMover;
        try {
            newMover = (AutomaticMovingCharacter) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class, MovementQueue.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info, queue);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newMover;
    }

    public MainCharacterEntity makeMainCharacter(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
        CollisionChart chart = collisionChart(type);

        MainCharacterEntity main;
        try {
            main = (MainCharacterEntity) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return main;
    }

    public CollidableEntity makeCollidable(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info) {
        CollisionChart chart = collisionChart(type);

        CollidableEntity newCollidable;
        try {
            newCollidable = (CollidableEntity) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newCollidable;
    }

    private CollisionChart collisionChart(String type){
        CollisionChartGetter collisionChartGetter = new DefaultCollisionChartGetter();
        return collisionChartGetter.getCollisionChart(decoder, type);
    }
}

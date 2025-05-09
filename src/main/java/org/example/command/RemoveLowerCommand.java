package org.example.command;

import org.example.manager.CollectionManager;
import org.example.model.Ticket;
import org.example.model.generator.TicketInput;

import java.util.Iterator;

/**
 * <p>Команда для удаления всех элементов коллекции {@link Ticket},
 * которые меньше заданного сгенерированного элемента.</p>
 *
 * <p>Сравнение производится на основе метода {@link Ticket#compareTo(Ticket)}.</p>
 *
 * @author kdseum9
 * @version 1.0
 */
public class RemoveLowerCommand extends AbstractCommand {

    /**
     * <p>Выполняет команду <code>remove_lower</code>.</p>
     * <p>Удаляет все элементы коллекции, которые "меньше" случайно сгенерированного {@link Ticket}.</p>
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер, управляющий коллекцией
     * @return результат выполнения команды (null)
     */
    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        Ticket referenceTicket = TicketInput.generateTicket();
        logger.info("Reference ticket for comparison: {}", referenceTicket);

        int removedCount = 0;

        Iterator<Ticket> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            Ticket currentTicket = iterator.next();
            if (referenceTicket.compareTo(currentTicket) > 0) {
                iterator.remove();
                removedCount++;
                logger.info("Removed ticket: {}", currentTicket);
                System.out.println("Deleted ticket: " + currentTicket);
            }
        }

        if (removedCount == 0) {
            System.out.println("No tickets were lower than the reference ticket.");
            logger.info("No tickets were removed.");
        } else {
            System.out.println("Total tickets removed: " + removedCount);
            logger.info("Total tickets removed: {}", removedCount);
        }

        return null;
    }
}

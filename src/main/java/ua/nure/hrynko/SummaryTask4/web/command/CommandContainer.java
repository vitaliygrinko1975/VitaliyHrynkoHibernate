package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 *
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<>();
	
	static {
		// common commands
        commands.put("welcome", new WelcomeCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		
		// client commands
		commands.put("listMenu", new ListCarCommand());
		commands.put("makeOrderCommand", new MakeOrdersPageCommand());
		commands.put("toPay", new ReturnCommand());
		commands.put("loginingPage", new LoginingPageCommand());
		commands.put("loginingUser", new LoginingCommand());
		commands.put("sortedUpPrice", new SortedCarUpPriceCommand());
		commands.put("sortedDownPrice", new SortedCarDownPriceCommand());
		commands.put("sortedUpName", new SortedCarUpNameCommand());
		commands.put("sortedDownName", new SortedCarDownNameCommand());
        commands.put("selectByClass", new SelectCarByClass());

		// manager commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("blockManagerCommand", new  BlockManagerCommand());
		commands.put("unBlockManagerCommand", new  UnBlockManagerCommand());

		// admin commands
		commands.put("listAdmin", new ListAdminCommand());
		commands.put("removeCommand", new RemoveCarCommand());
		commands.put("adminPageUpdateCommand", new AdminPageUpdateCarCommand());
		commands.put("adminUpdateCar", new AdminUpdateCarCommand());
		commands.put("addPage", new AdminPageAddCarCommand());
		commands.put("addCar", new AddCarCommand());
		commands.put("pageUsers", new AdminPageUsers());
		commands.put("addManager", new AddManagerCommand());
		commands.put("adminPageAddManager", new AdminPageAddManagerCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}
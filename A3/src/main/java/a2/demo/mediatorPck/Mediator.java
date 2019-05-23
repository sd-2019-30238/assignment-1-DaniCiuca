package a2.demo.mediatorPck;

import a2.demo.command.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

class Mediator implements IMediator {
    private List<ICommandHandler> handlers;
    private Map<String, ICommandHandler> commands;

    public Mediator() {
        handlers = new ArrayList<ICommandHandler>();
        commands = new HashMap<String, ICommandHandler>();
        commands.put("registerUser", new RegisterUserCommandHandler());
        commands.put("registerStaff", new RegisterStaffCommandHandler());
        commands.put("loginUser",new LoginUserCommandHandler());
        commands.put("loginStaff",new LoginStaffCommandHandler());
        commands.put("filterAuthors",new FilterAuthorsCommandHandler());
        commands.put("filterTitles",new FilterTitlesCommandHandler());
        commands.put("filterGenres",new FilterGenresCommandHandler());
        commands.put("borrowBook",new BorrowBookCommandHandler());
        commands.put("returnBook",new ReturnBookCommandHandler());

    }

    @Override
    public void addHandler(ICommandHandler handler) {
        handlers.add(handler);
    }

    @Override
    public void mediate(ICommand command) {
        ICommandHandler handler = commands.get(command.getCommandName());
        handler.execute(command);
    }
}

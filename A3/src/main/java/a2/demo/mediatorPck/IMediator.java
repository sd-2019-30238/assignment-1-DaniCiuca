package a2.demo.mediatorPck;

import a2.demo.command.ICommand;
import a2.demo.command.ICommandHandler;

public interface IMediator {
    public void mediate(ICommand command);

    public void addHandler(ICommandHandler handler);
}

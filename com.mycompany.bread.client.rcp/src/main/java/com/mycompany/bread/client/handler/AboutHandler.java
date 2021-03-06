/**
 * Copyright © 2018 A.P. (email@email.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.bread.client.handler;

import javax.inject.Named;
import javax.persistence.metamodel.Metamodel;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.mycompany.bread.domain.EntityManagerHelper;

/**
 * Action: Open dialog with "about" information
 * @author ap
 */
public class AboutHandler
{
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL)
    Shell shell)
    {
        Metamodel datamodel = EntityManagerHelper.getEntityManager().getMetamodel();
        MessageDialog.openInformation(shell, "About", "Eclipse 4 Application example.");
    }
}

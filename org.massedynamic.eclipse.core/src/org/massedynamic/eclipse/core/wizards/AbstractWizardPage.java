/**
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

package org.massedynamic.eclipse.core.wizards;

import org.eclipse.jface.wizard.WizardPage;

/**
 * Abstract base {@link WizardPage} class that is owned by a {@link AbstractWizard}.
 *
 * @author Mark Masse
 */
public abstract class AbstractWizardPage extends WizardPage {

    private final AbstractWizard _Wizard;

    /**
     * Constructor.
     *
     * @param wizard The {@link AbstractWizard}.
     */
    public AbstractWizardPage(AbstractWizard wizard) {
        super(wizard.getWindowTitle());
        _Wizard = wizard;
        setTitle(wizard.getWindowTitle());
        setDescription(wizard.getDefaultPageDescription());
    }

    /**
     * Returns the wizard.
     * 
     * @return The {@link AbstractWizard}.
     */
    public AbstractWizard getWizard() {
        return _Wizard;
    }

}
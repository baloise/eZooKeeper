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

package org.massedynamic.eclipse.jmx.editors;

import org.massedynamic.eclipse.core.editors.DataModelManagerEditorInput;
import org.massedynamic.eclipse.jmx.data.MBean;
import org.massedynamic.eclipse.jmx.model.DomainModel;
import org.massedynamic.eclipse.jmx.model.MBeanModel;

import javax.management.ObjectName;

public class DomainMBeanModelManagerEditorInput extends DataModelManagerEditorInput<MBeanModel, ObjectName, MBean> {

    private DomainModel _DomainModel;

    public DomainMBeanModelManagerEditorInput(String editorId, DomainModel domainModel) {
        super(editorId, domainModel.getMBeanModelManager());
        _DomainModel = domainModel;
    }

    @Override
    public String getName() {
        return _DomainModel.getData().getName();
    }

    @Override
    public String toString() {
        return getClass().getName() + " [DomainModel=" + _DomainModel + "]";
    }

}
